package nl.tudelft.atlarge.gdeploy.reports.recreation

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

var replacements = mutableMapOf<String, String>()
var workdir = "./"

fun defaultMetadataEdit(option: String) {
    while (true) {
        println("Please enter what you want to do with $option\n" +
                "'d','del'\tDelete this metadata option default\n" +
                "'e','edit'\tEdit this metadata option default\n" +
                "'s','show'\tShow the current value\n" +
                "'q','quit'\tQuit this menu and go back to the default metadata menu")

        val opt = readLine().orEmpty()
        when (opt.toLowerCase()) {
            "d", "del" -> {
                replacements.remove(option)
                return
            }
            "s", "show" ->
                println("\"${replacements.getOrDefault(option, "")}\"")
            "e", "edit" -> {
                println("Please enter the new value for this metadata option default")
                val newValue = readLine()
                when (newValue) {
                    null -> println("Something went wrong, please try again...")
                    else -> replacements[option] = newValue
                }
            }
            "q", "quit" ->
                return
        }
    }
}

fun chooseMetadataName() {
    println("Please enter the name of the metadata to add/edit")

    val name = readLine().orEmpty().toUpperCase()
    when {
        name.isEmpty() -> println("Something went wrong, please try again")
        replacements.containsKey(name!!.toUpperCase()) -> defaultMetadataEdit(name.toUpperCase())
        else -> {
            println("Replacement '${name.toUpperCase()}' not found yet\n" +
                    "Please enter its (string) value")
            val value = readLine()
            when (value) {
                null -> println("Something went wrong, please try again")
                else -> {
                    println("Set value to '$value'")
                    replacements[name] = value
                }
            }
        }
    }
}

fun defaultMetadataMenu() {
    while (true) {
        println("You are now in the default metadata menu, please enter what you want to do.\n" +
                "'q','quit'\tQuit this menu and go back to the main menu\n" +
                "'l','list'\tList the current list of default metadata\n" +
                "'e','edit'\tEdit a specific metadata entry or add it")

        val option = readLine().orEmpty()
        when (option.toLowerCase()) {
            "quit", "q" ->
                return
            "list", "ls", "l" ->
                replacements.forEach { println("${it.key}\t-\t${it.value}") }
            "edit", "e" ->
                chooseMetadataName()
            else ->
                println("Unrecognized option, please try again")
        }
    }
}

fun changeWorkingDirectoryMenu() {
    println("Please enter the new working directory")

    val newWorkDir = readLine().orEmpty()
    when {
        newWorkDir.isEmpty() ->
            println("You cannot enter nothing.")
        Files.isDirectory(Paths.get(newWorkDir)) -> {
            println("Setting new working directory to '$newWorkDir'")
            workdir = newWorkDir
            return
        }
        else ->
            println("Directory '$newWorkDir' does not exist!")
    }
}

fun workingDirectoryEditMenu() {
    println("The current working directory is $workdir\n" +
            "Do you really want to change this? y/n")

    while (true) {
        when (readLine().orEmpty().toLowerCase()) {
            "y", "yes" ->
                return changeWorkingDirectoryMenu()
            "n", "no" ->
                return
            else ->
                println("Invalid argument, expected 'y' or 'n'")
        }
    }
}

fun mainMenu(): Int {
    val mainMenuString =
            "You are now in the main menu, please type what you want to do\n" +
            "'d','default'\tEdit the default metadata\n" +
            "'w','workdir'\tEdit the working directory\n" +
            "'r','run'\t\tRun the metadata generation process\n" +
            "'q','quit'\t\tQuit this program"

    while (true) {
        println(mainMenuString)

        val option = readLine()!!
        when (option.toLowerCase()) {
            "d", "def", "default" ->
                defaultMetadataMenu()
            "w", "wd", "workdir" ->
                workingDirectoryEditMenu()
            "r", "run" ->
                MetadataRepairer(File(workdir)).repair(replacements)
            "q", "quit" ->
                return 0
        }
    }
}

fun main(args: Array<String>) {
    mainMenu()
}
