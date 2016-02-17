/* Jonathan Jensen
** Assignment 3 Task 1
** Java Class Skeletons
** CS321
*/

class Class {
    private boolean isAbstract;
    private String  name;
    private Args    args;
    private Class[] subclasses;

    //default constructor

    Class(boolean isAbstract, String name, Args args, Class[] subclasses) {
        this.isAbstract = isAbstract;
        this.name = name;
        this.args = args;
        this.subclasses = subclasses;
    }

    //methods and fields go here ...
}

class Args {
    Args before;
    Args last;

    //default constructor
    
    Args(Args before, Args last) {
        this.before = before;
        this.last = last;
    }
    
    //methods and fields go here ...
}

class Arg {
    private String visibility;
    private Type type;
    private String id;

    //default constructor

    Arg(String visibility, Type type, String id) {
        this.visibility = visibility;
        this.type = type;
        this.id = id;
    }

    //methods and fields go here ...
}

abstract class Type {
    
}

class NamedType extends Type {
    private String[] ids;
    //default constructor for this class
    NamedType(String[] ids) {
        this.ids = ids;
    }
    //methods and fields go here
}

class ArrayType {
    private Type elem;
    //default constructor for this class
    ArrayType(Type elem) {
        this.elem = elem;
    }
}
