package z

import zio.{ExitCode, URIO}
import zio.console.{getStrLn, putStrLn}

object Hello extends zio.App {
  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = prog.exitCode

  val prog = for {
    _ <- putStrLn("hello, whats your name")
    name <- getStrLn
    _ <- putStrLn(s"Hello $name")
  } yield ()
}
