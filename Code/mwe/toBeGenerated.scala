class Figure(val nr: Int, val url: String)

val entity11 = new Figure(1, "")
val content = s"Lorem Ipsum mit Referenz auf ${entity11.nr}."

println(content)
