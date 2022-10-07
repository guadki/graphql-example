package sangria.book

object BookCategory {

  sealed trait BookCategory
  case object Biography extends BookCategory
  case object History extends BookCategory
  case object Romance extends BookCategory
  case object SciFi extends BookCategory

}