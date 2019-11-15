import java.math.BigDecimal
import java.time.DayOfWeek
import java.time.LocalDateTime

class OrdersAnalyzer {

    data class Order(val orderId: Int, val creationDate: LocalDateTime, val orderLines: List<OrderLine>)

    data class OrderLine(val productId: Int, val name: String, val quantity: Int, val unitPrice: BigDecimal)

    fun totalDailySales(orders: List<Order>): Map<DayOfWeek, Int> {
        val ordersByDay = orders.groupBy { it.creationDate.dayOfWeek }
        return ordersByDay.mapValues { dayOrders -> dayOrders.value.sumBy { order -> order.orderLines.sumBy { it.quantity } } }
    }
}