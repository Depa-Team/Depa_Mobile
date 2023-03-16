package Beans

class Flats {
    var flatName:String
    var managerId:Int
    var guestId:Int?
    var initialDate:String?
    var endDate:String?
    var status:Boolean
    var price:Int?
    var id:Int

    constructor(flatName: String, managerId: Int, guestId: Int?, initialDate: String?, endDate: String?, status: Boolean, price: Int?, id: Int
    ) {
        this.flatName = flatName
        this.managerId = managerId
        this.status = status
        this.id = id
        if(status){
            this.guestId = 0
            this.initialDate = " "
            this.endDate = " "
            this.price = 0
        }
        else{
            this.guestId = guestId
            this.initialDate = initialDate
            this.endDate = endDate
            this.price = price

        }

    }
}