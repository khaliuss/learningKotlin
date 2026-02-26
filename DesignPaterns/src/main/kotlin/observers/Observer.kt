package observers

interface Observer<T> {
    fun onChange(newValue:T)
}