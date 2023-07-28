package project.rawg.core.converter

interface BaseDataConverter<R, D> {
    fun convert(response: R): D
}

fun <R, D> List<R>.convertList(converter: BaseDataConverter<R, D>): List<D> {
    return map { converter.convert(it) }
}
