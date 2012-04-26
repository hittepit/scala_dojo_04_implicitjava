package dojo

import java.util.{ArrayList, List}
import com.google.common.base.Predicate
import scala.collection.JavaConversions._

class UserLookup(dataSource :DataSource) extends JUserLookup {
	implicit def funcToPredicate(f:User=>Boolean) =new Predicate[User]{
    	def apply(user:User) = f(user)
    } 
	
  /*
     Once this is passing by using the predicate class
     can you do it in the Scala way by passing in a function?
     Is there a way to imlicit[ly] convert a function to a Predicate?
   */
  def olderThan(age :Int): List[User] = {
    dataSource.findUsers((u:User)=>u.getAge > age)
  }

  /*
      Are there standard JavaConversions to make it easier to work with Java collections?
   */
  def namesYoungerThan(age: Int):List[String] = dataSource.findUsers((u:User)=>u.getAge<age).map(_.getName)

  def allFemale(): List[String] = dataSource.findUsers((u:User) => !u.isMale()).map(_.getName)

  def allEligible() = dataSource.findUsers((u:User) => u.isMale() && u.getAge()>34)

}
