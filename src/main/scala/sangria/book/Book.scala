package sangria.book

import sangria.book.BookCategory.BookCategory

case class Book(
                 id: Int,
                 isbn: String,
                 title: String,
                 year: String,
                 category: BookCategory)
