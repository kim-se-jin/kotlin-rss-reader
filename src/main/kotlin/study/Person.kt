package study

class Skill(
    var level: String = "",
    var description: String = "",
)

class Person(
    val name: String = "",
    val company: String = "",
    val skills: List<Skill>,
    val languages: List<Language>,
)

data class Language(
    val name: String,
    val level: Int,
)

class PersonBuilder(
    var name: String = "",
    var company: String = "",
    private val skillsList: MutableList<Skill> = mutableListOf<Skill>(),
    private val languagesList: MutableList<Language> = mutableListOf(),
) {
    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun build(): Person = Person(name, company, skillsList, languagesList)

    fun skills(block: SkillBuilder.() -> Unit) {
        val builder = SkillBuilder()
        builder.block()
        skillsList.addAll(builder.build())
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        val builder = LanguageBuilder()
        builder.block()
        languagesList.addAll(builder.build())
    }
}

// SkillBuilder
class SkillBuilder {
    private val skills = mutableListOf<Skill>()

    fun soft(description: String) {
        skills.add(Skill("soft", description))
    }

    fun hard(description: String) {
        skills.add(Skill("hard", description))
    }

    fun build(): List<Skill> = skills
}

class LanguageBuilder {
    private val languages = mutableListOf<Language>()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): List<Language> = languages
}
