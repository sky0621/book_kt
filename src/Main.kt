import java.util.*
import kotlin.system.exitProcess

fun usage() {
    val msg = """

[usage]
キーバリュー形式で文字列情報を管理するコマンドです。
以下のサブコマンドが利用可能です。

list   ... 保存済みの内容を一覧表示します。
save   ... keyとvalueを渡して保存します。
get    ... keyを渡してvalueを表示します。
remove ... keyを渡してvalueを削除します。
help   ... ヘルプ情報（当内容と同じ）を表示します。

        """.trimIndent()
    println(msg)
}

// -------------------------------------------------------------------
// ここからメイン処理
// -------------------------------------------------------------------

var cmdStore = mutableMapOf<String, String>()

fun main(vararg args: String) {
    println("Start!")
    while (true) {
        val s = Scanner(System.`in`)
        val cmd = s.nextLine()

        // アプリ終了判定
        if (cmd == "end") {
            println("End!")
            exitProcess(-1)
        }

        // ヘルプ
        if (cmd == "help") {
            usage()
            continue
        }

        if (cmd == "") {
            usage()
            continue
        }

        // 以降は、引数ありコマンドの処理
        val cmds = cmd.split(" ")

        // 保存
        if (cmds[0] == "save") {
            if (cmds.size != 3) {
                usage()
                continue
            }
            cmdStore[cmds[1]] = cmds[2]
        }

        // 取得
        if (cmds[0] == "get") {
            if (cmds.size != 2) {
                usage()
                continue
            }
            println(cmdStore[cmds[1]])
        }

        // 削除
        if (cmds[0] == "remove") {
            if (cmds.size != 2) {
                usage()
                continue
            }
            cmdStore.remove(cmds[1])
        }

        // 一覧
        if (cmds[0] == "list") {
            println("\"key\",\"value\"")
            cmdStore.forEach { k, v -> println("\"$k\",\"$v\"") }
        }
    }
}
