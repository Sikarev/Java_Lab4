package models;

import org.apache.commons.lang3.tuple.Triple;
/**
 *
 * @author roman-
 * PersonModel contains fields with id,name,gender, subdivision and salary. Also it has constructors in order to create an object
 */
public class PersonModel {
    public int id;
    public String name;
    public String isMale;
    SubDivisionModel subDivisionModel;
    public int salary;
    Triple<Integer, Integer, Integer> date;
    /**
     *PersonModel here is constructor for an object
     * @id identificator of human
     * @name name of human
     * @isMale checking if its male
     * @subDivisionModel show subdivisions of human
     * @salary containts salary of human
     */
    public PersonModel(int id, String name, String isMale, SubDivisionModel subDivisionModel, int salary, Triple<Integer, Integer, Integer> date) {
        this.id = id;
        this.name = name;
        this.isMale = isMale;
        this.subDivisionModel = subDivisionModel;
        this.salary = salary;
        this.date = date;
    }
    /**
     *overriding method ToString in order to convert data
     */
    @Override
    public String toString() {
        return new String("PersonModel {\nid: " + id + "\nname: " + name + "\nisMale: "
                + isMale + "\nsubDivision: " + subDivisionModel.toString() + "\nsalary: " + salary
                + "\ndate: " + date.getLeft() + "." + date.getMiddle() + "." + date.getRight() + "." + "\n}");
    }
    /**
     *overriding method equals in order to compare person with each other
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        PersonModel model = (PersonModel) obj;
        return (this.id == model.id && this.name.equals(model.name) &&
                this.isMale == model.isMale && this.subDivisionModel.equals(model.subDivisionModel)
                && this.salary == model.salary && this.date.equals(model.date));
    }
    /**
     * Overriding hashCode method
     **/
    @Override
    public int hashCode()
    {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + id * salary * date.getLeft()*date.getRight()*date.getMiddle();
        return result;
    }
}
