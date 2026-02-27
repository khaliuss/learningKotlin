import kotlin.concurrent.thread
import kotlin.random.Random

// Репозиторий данных
object DataRepository {

    val userData = MutableObservable<String>("User_Initial")
    val orderData = MutableObservable<Int>(100)
    val priceData = MutableObservable<Double>(99.99)

    // Метод обновления данных
    fun updateData(newUser: String? = null, newOrder: Int? = null, newPrice: Double? = null) {
        newUser?.let {
            userData.data = it
        }

        newOrder?.let {
            orderData.data = it
        }

        newPrice?.let {
            priceData.data = it
        }
    }





}

// Класс для автоматического обновления данных
class DataUpdater(private val repository: DataRepository) {
    init {
        thread {
            while (true) {
                when (Random.nextInt(3)) {
                    0 -> repository.updateData(newUser = "User_${Random.nextInt(1, 100)}")
                    1 -> repository.updateData(newOrder = Random.nextInt(100, 200))
                    2 -> repository.updateData(newPrice = Random.nextDouble(50.0, 150.0))
                }
                val millis = Random.nextLong(10000L)
                Thread.sleep(millis) // Симуляция времени между обновлениями
            }
        }
    }
}



fun main() {
    // Запуск обновления данных
    DataUpdater(DataRepository)

    // Запуск мониторинга
    // Подписка на обновления данных пользователя
    DataRepository.userData.registerObserver { newValue ->
        println("UserMonitor: Обнаружено изменение данных пользователя: $newValue")
    }

    // Подписка на обновления данных заказов
    DataRepository.orderData.registerObserver { newValue ->
        println("OrderMonitor: Обнаружено изменение данных заказа: $newValue")
    }

    // Подписка на обновления данных цены
    DataRepository.priceData.registerObserver { newValue ->
        println("PriceMonitor: Обнаружено изменение цены: $newValue")
    }
}


fun interface Observer<T>{
    fun onChange(newItems:T)
}

class MutableObservable<T>(initialization:T):Observable<T>{

    override var data = initialization
        set(value:T){
            field = value
            notifyDataChanged()
        }


    private val _observers = mutableListOf<Observer<T>>()

    override val observers
        get() = _observers.toList()


    override fun registerObserver(observer:Observer<T>){
        _observers.add(observer)
        observer.onChange(data)
    }

    override fun unregisterObserver(observer:Observer<T>){
        _observers.remove(observer)
    }

}

interface Observable<T>{

    val data:T
    val observers:List<Observer<T>>


    fun registerObserver(observer:Observer<T>)

    fun unregisterObserver(observer:Observer<T>)


    fun notifyDataChanged(){
        for(observer in observers){
            observer.onChange(data)
        }
    }
}
