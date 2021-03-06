package com.evolutiongaming.scassandra

import cats.Contravariant

trait ToCql[-A] {

  def apply(a: A): String
}

object ToCql {

  implicit val contravariantToCql: Contravariant[ToCql] = new Contravariant[ToCql] {
    def contramap[A, B](fa: ToCql[A])(f: B => A) = fa.contramap(f)
  }

  def apply[A](implicit toCql: ToCql[A]): ToCql[A] = toCql

  def apply[A: ToCql](a: A): String = ToCql[A].apply(a)


  implicit val strToCql: ToCql[String] = (a: String) => a


  @deprecated("use implicits instead", "2.0.5")
  object Ops {

    @deprecated("use implicits.IdOpsToCql instead", "2.0.5")
    implicit class IdOps[A](val self: A) extends AnyVal {

      def toCql(implicit toCql: ToCql[A]): String = implicits.IdOpsToCql(self).toCql
    }
  }


  object implicits {

    implicit class IdOpsToCql[A](val self: A) extends AnyVal {

      def toCql(implicit toCql: ToCql[A]): String = ToCql(self)
    }
  }


  implicit class ToCqlOps[A](val self: ToCql[A]) extends AnyVal {

    final def contramap[B](f: B => A): ToCql[B] = (a: B) => self(f(a))
  }
}