package sangria

import sangria.book.BookCategory.BookCategory
import sangria.book.{Book, BookCategory, BookRepository}
import sangria.schema._

object SchemaDefinition {

  val BookCategoryEnum: EnumType[BookCategory] = {
    EnumType(
      "BookCategory",
      Some("Category of a book"),
      List(
        EnumValue(
          "Biography",
          value = BookCategory.Biography
        ),
        EnumValue(
          "History",
          value = BookCategory.History
        ),
        EnumValue(
          "Romance",
          value = BookCategory.Romance
        ),
        EnumValue(
          "SciFi",
          value = BookCategory.SciFi
        ),
      )
    )
  }

  val Book: ObjectType[BookRepository, Book] = {
    ObjectType(
      "Book",
      fields[BookRepository, Book](
        Field("id", IntType, resolve = _.value.id),
        Field("isbn", StringType, resolve = _.value.isbn),
        Field("title", StringType, resolve = _.value.title),
        Field("year", StringType, resolve = _.value.year),
        Field("category", BookCategoryEnum, resolve = _.value.category)
      )
    )
  }

  val ID: Argument[Int] = Argument("id", IntType)
  val LimitArg: Argument[Int] = Argument("limit", OptionInputType(IntType), defaultValue = 5)
  val OffsetArg: Argument[Int] = Argument("offset", OptionInputType(IntType), defaultValue = 0)

  val Query: ObjectType[BookRepository, Unit] = {
    ObjectType(
      "Query",
      fields[BookRepository, Unit](
        Field(
          "book",
          OptionType(Book),
          arguments = ID :: Nil,
          resolve = c => c.ctx.getBook(c.arg(ID))),
        Field(
          "books",
          ListType(Book),
          arguments = LimitArg :: OffsetArg :: Nil,
          resolve = c => c.ctx.getBooks(c.arg(LimitArg), c.arg(OffsetArg)))
      )
    )
  }

  val BookSchema: Schema[BookRepository, Unit] = Schema(Query)

}
