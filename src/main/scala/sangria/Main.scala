package sangria

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import sangria.book.BookRepository
import sangria.execution.{ErrorWithResolver, Executor, QueryAnalysisError}
import sangria.http.akka.circe.CirceHttpSupport
import sangria.marshalling.circe._
import scala.util.{Failure, Success}

object Main extends App with CirceHttpSupport {

  implicit val system: ActorSystem = ActorSystem("sangria-server")

  import system.dispatcher

  val route: Route = path("graphql") {
    prepareGraphQLRequest {
      case Success(req) =>
        val graphQLResponse = Executor.execute(
          schema = SchemaDefinition.BookSchema,
          queryAst = req.query,
          userContext = new BookRepository,
          variables = req.variables,
          operationName = req.operationName)
          .map(OK -> _)
          .recover {
            case error: QueryAnalysisError => BadRequest -> error.resolveError
            case error: ErrorWithResolver => InternalServerError -> error.resolveError
          }
        complete(graphQLResponse)
      case Failure(preparationError) => complete(BadRequest, formatError(preparationError))
    }
  }

  Http().newServerAt("0.0.0.0", 8080).bind(route)

}
