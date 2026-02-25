package org.example.extentions

inline fun <T,R> Iterable<T>.transform(operation:(T)->R): List<R>{
    val result= mutableListOf<R>()
    for (item in this){
        result.add(operation(item))
    }
    return result
}

inline fun <T> Iterable<T>.filter(isSuitable: (T) -> Boolean): List<T> {
    val filteredProductCards = mutableListOf<T>()
    for (item in this) {
        if (isSuitable(item)) {
            filteredProductCards.add(item)
        }
    }
    return filteredProductCards
}

inline fun <T> Iterable<T>.myForEach(operation:(T)-> Unit){
    for (it in this){
        operation(it)
    }
}

inline fun <T, R> T.myLet(block: (T) -> R): R{
    return block(this)
}


inline fun<T>  T.myAlso(block : (T)-> Unit):T{
    block(this)
    return this
}

inline fun<T>  T.myApply(block : T.()-> Unit):T{
    block()
    return this
}

inline fun<T,R>  myWith(element:T, block : T.()-> R):R{
    return element.block()
}

inline fun<T,R>  T.myRun(block : T.()-> R):R{
    return block()
}