package Beans

class Usuarios {

    var email:String
    var password:String
    var type:String
    var plan:String
    var name:String
    var id:Int

    constructor(email: String, password: String, type: String, plan: String, name: String, id: Int) {
        this.email = email
        this.password = password
        this.type = type
        this.plan = plan
        this.name = name
        this.id = id
    }
}