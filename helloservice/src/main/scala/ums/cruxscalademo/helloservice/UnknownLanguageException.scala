package ums.cruxscalademo.helloservice

/**
  * Exception thrown indicating that the specified language is unknown.
  *
  * @author psp
  * @since 2016.07.14
  */
class UnknownLanguageException(msg: String) extends RuntimeException(msg)
