package observers

fun interface Observer<T> {
    fun onChange(newValue:T)
}