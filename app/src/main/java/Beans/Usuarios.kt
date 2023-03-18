package Beans

class Usuarios {

    var email:String
    var password:String
    var type:String
    var phoneNumber: Int
    var plan:String
    var name:String
    var id:Int

    constructor(email: String, password: String, type: String,phoneNumber: Int, plan: String, name: String, id: Int) {
        this.email = email
        this.password = password
        this.type = type
        this.phoneNumber=phoneNumber
        this.plan = plan
        this.name = name
        this.id = id
    }
}