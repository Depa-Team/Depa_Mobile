package Beans

class Flats {
    var flatName:String
    var managerId:Int
    var guestId:Int
    var initialDate:String
    var endDate:String
    var status:Boolean
    var price:Int
    var id:Int

    constructor(flatName: String, managerId: Int, guestId: Int, initialDate: String, endDate: String, status: Boolean, price: Int, id: Int
    ) {
        this.flatName = flatName
        this.managerId = managerId
        this.guestId = guestId
        this.initialDate = initialDate
        this.endDate = endDate
        this.status = status
        this.price = price
        this.id = id
    }
}