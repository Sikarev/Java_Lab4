package models;
/**
 *
 * @author roman-
 * СlassSubDivisionModel containts data about subdivision
 * it contains id and name fields, and constructors
 */
public class SubDivisionModel {
    public int id;
    public String name;

    public SubDivisionModel(String name, int id) {
        this.name = name;
        this.id = id;
    }

    /**
     *
     * overriding toString method in order to conver data(SubDivision column and
     */
    @Override
    public String toString() {
        return new String("SubDivisionModel {\nid: " + id + "\nname: " + name + "\n}");
    }

    /**
     *
     * overriding equals method
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

        SubDivisionModel model = (SubDivisionModel) obj;
        return (this.id == model.id && this.name.equals(model.name));
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + id;
        return result;
    }
}
