module languages/Java-1.5/types/types/references

imports
	
	include/Java
	lib/task/-
	lib/types/-
	lib/properties/-
	lib/relations/-

signatures

	RefType : TypeName * TypeParams -> Type

	Reference    : TypeKind
	Class        : TypeKind
	Interface    : TypeKind
	Array        : TypeKind
	
	Final        : TypeKind
	
	Object       : TypeKind 
	Cloneable    : TypeKind
	Serializable : TypeKind
	String       : TypeKind
	
type rules // Reference types

	ClassOrInterfaceType(c, tp*) : RefType(c, tp*)
	ClassType(c, tp*)            : RefType(c, tp*)
	InterfaceType(c, tp*)        : RefType(c, tp*)
	
	ArrayType(ty) : ArrayType(ty)

relations // Reference type kinds

	RefType(_, _) <is: Reference()
	ArrayType(_)  <is: Reference()
	Null()        <is: Reference()

	RefType(c, _) <is: Class()
	where definition of c has kind Class()
		
	RefType(c, _) <is: Interface()
	where definition of c has kind Interface()

	ArrayType(_)  <is: Array()
	
	RefType(c, _) <is: Final()
	where c has modifiers Final()
	
relations // Built in type kinds

	RefType("Object", _)       <is: Object()
	RefType("Cloneable", _)    <is: Cloneable()
	RefType("Serializable", _) <is: Serializable()
	RefType("String", _)       <is: String()
