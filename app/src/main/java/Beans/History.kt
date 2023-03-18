package Beans

class History {
    var id:Int
    var flatName:String
    var managerId:Int
    var guestName:String
    var initialDate:String
    var endDate:String
    var price:Int

    constructor(
        id: Int,
        flatName: String,
        managerId: Int,
        guestName: String,
        initialDate: String,
        endDate: String,
        price: Int
    ) {
        this.id = id
        this.flatName = flatName
        this.managerId = managerId
        this.guestName = guestName
        this.initialDate = initialDate
        this.endDate = endDate
        this.price = price
    }
}