package sangria.book

import sangria.book.BookCategory._


class BookRepository {

  import sangria.book.BookRepository._

  def getBook(id: Int): Option[Book] = books.find(_.id == id)

  def getBooks(limit: Int, offset: Int): Vector[Book] = books.slice(offset, offset + limit)

}

object BookRepository {
  private val books = Vector(
    Book(1, "21312424214", "snort", "1984", SciFi),
    Book(10, "21314526214", "port", "2015", Biography),
    Book(12, "78542424214", "court", "2018", Biography),
    Book(17, "25692424214", "no", "1453", History),
    Book(119, "22538424214", "idea", "1410", SciFi),
    Book(140, "21759624214", "mock", "996", History),
    Book(1178, "21312422758", "broke", "1025", Romance)
  )

}
