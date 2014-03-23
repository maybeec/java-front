package org.metaborg.java.conformance;

import static org.metaborg.java.conformance.util.TermTools.*;

import org.spoofax.interpreter.terms.IStrategoTerm;

import com.google.common.base.Predicate;

public class JavaTermProjections {
	public static IStrategoTerm getTypeImport(IStrategoTerm term) {
		if(isAppl(term, "TypeImportDec")) {
			return term.getSubterm(0);
		}
		return null;
	}

	public static IStrategoTerm getTypesInPackage(IStrategoTerm term) {
		final IStrategoTerm innerPackage = collectOne(term, new Predicate<IStrategoTerm>() {
			@Override
			public boolean apply(IStrategoTerm input) {
				return isAppl(input, "PackageDec", 3);
			}
		});

		if(innerPackage != null)
			return innerPackage.getSubterm(2);

		return term.getSubterm(1);
	}


	public static IStrategoTerm getSupertype(IStrategoTerm term) {
		final IStrategoTerm superClass = term.getSubterm(5);
		if(isAppl(superClass, "SuperDec"))
			return superClass.getSubterm(0);
		else
			return null;
	}

	public static IStrategoTerm getClassSuperinterfaces(IStrategoTerm term) {
		final IStrategoTerm superInterfaces = term.getSubterm(6);
		if(isList(superInterfaces))
			return superInterfaces;
		else
			return null;
	}

	public static IStrategoTerm getImplementsInterface(IStrategoTerm term) {
		return term.getSubterm(1);
	}


	public static IStrategoTerm getInterfaceSuperinterfaces(IStrategoTerm term) {
		final IStrategoTerm superInterfaces = term.getSubterm(4);
		if(isList(superInterfaces))
			return superInterfaces;
		else
			return null;
	}

	public static IStrategoTerm getExtendsInterface(IStrategoTerm term) {
		return term.getSubterm(1);
	}


	public static IStrategoTerm getClassBodyDeclarations(IStrategoTerm term) {
		return term.getSubterm(7).getSubterm(0);
	}


	public static IStrategoTerm getFieldType(IStrategoTerm field) {
		return field.getSubterm(3);
	}

	public static IStrategoTerm getFieldInit(IStrategoTerm field) {
		final IStrategoTerm varDec = field.getSubterm(4);
		if(varDec.getSubtermCount() == 1)
			return null;
		return varDec.getSubterm(1);
	}


	public static IStrategoTerm getMethodReturnType(IStrategoTerm method) {
		return method.getSubterm(4);
	}

	public static IStrategoTerm getMethodParams(IStrategoTerm method) {
		return method.getSubterm(6).getSubterm(0);
	}

	public static IStrategoTerm getMethodBody(IStrategoTerm method) {
		// TODO: check for abstract method.
		return getBlockStatements(method.getSubterm(8));
	}


	public static IStrategoTerm getConstructorParams(IStrategoTerm constructor) {
		return constructor.getSubterm(4);
	}

	public static IStrategoTerm getConstructorBody(IStrategoTerm constructor) {
		return constructor.getSubterm(6).getSubterm(1);
	}


	public static IStrategoTerm getBlockStatements(IStrategoTerm block) {
		if(isAppl(block, "NoMethodBody", 0))
			return null;
		return block.getSubterm(0);
	}

	
	public static IStrategoTerm getIfExpression(IStrategoTerm ifStmt) {
		return ifStmt.getSubterm(0);
	}
	
	public static IStrategoTerm getIfThenStatement(IStrategoTerm ifStmt) {
		return ifStmt.getSubterm(1);
	}
	
	public static IStrategoTerm getIfElseStatement(IStrategoTerm ifStmt) {
		if(ifStmt.getSubtermCount() == 2)
			return null;
		return ifStmt.getSubterm(2);
	}
	
	
	public static IStrategoTerm getForInitializers(IStrategoTerm forStmt) {
		return forStmt.getSubterm(0);
	}
	
	public static IStrategoTerm getForCondition(IStrategoTerm forStmt) {
		if(isAppl(forStmt.getSubterm(1), "None", 0))
			return null;
		return forStmt.getSubterm(1);
	}
	
	public static IStrategoTerm getForUpdaters(IStrategoTerm forStmt) {
		return forStmt.getSubterm(2);
	}
	
	public static IStrategoTerm getForBody(IStrategoTerm forStmt) {
		return forStmt.getSubterm(3);
	}
	
	
	public static IStrategoTerm getWhileCondition(IStrategoTerm whileStmt) {
		return whileStmt.getSubterm(0);
	}
	
	public static IStrategoTerm getWhileBody(IStrategoTerm whileStmt) {
		return whileStmt.getSubterm(1);
	}
	
	
	public static IStrategoTerm getDoBody(IStrategoTerm doStmt) {
		return doStmt.getSubterm(0);
	}
	
	public static IStrategoTerm getDoCondition(IStrategoTerm doStmt) {
		return doStmt.getSubterm(1);
	}
	
	
	public static IStrategoTerm getTryBody(IStrategoTerm tryStmt) {
		return tryStmt.getSubterm(0);
	}
	
	public static IStrategoTerm getTryCatches(IStrategoTerm tryStmt) {
		return tryStmt.getSubterm(1);
	}	
	
	public static IStrategoTerm getTryFinally(IStrategoTerm tryStmt) {
		if(!isAppl(tryStmt, "Try", 3))
			return null;
		return tryStmt.getSubterm(2);
	}
	
	
	public static IStrategoTerm getCatchBody(IStrategoTerm catchStmt) {
		return catchStmt.getSubterm(1);
	}
	

	public static IStrategoTerm getParamType(IStrategoTerm param) {
		return param.getSubterm(1);
	}


	public static IStrategoTerm getArrayAccessArrayExpr(IStrategoTerm arrayAccess) {
		return arrayAccess.getSubterm(0);
	}

	public static IStrategoTerm getArrayAccessIndexExpr(IStrategoTerm arrayAccess) {
		return arrayAccess.getSubterm(1);
	}


	public static IStrategoTerm getAssignLeftExpr(IStrategoTerm assign) {
		return assign.getSubterm(0);
	}

	public static IStrategoTerm getAssignRightExpr(IStrategoTerm assign) {
		return assign.getSubterm(1);
	}


	public static IStrategoTerm getCastType(IStrategoTerm cast) {
		return cast.getSubterm(0);
	}

	public static IStrategoTerm getCastExpr(IStrategoTerm cast) {
		return cast.getSubterm(1);
	}


	public static IStrategoTerm getConsInvokeType(IStrategoTerm consInvoke) {
		if(isAppl(consInvoke, "QNewInstance"))
			return consInvoke;
		return consInvoke.getSubterm(2);
	}

	public static IStrategoTerm getConsInvokeArgs(IStrategoTerm consInvoke) {
		if(isAppl(consInvoke, "QNewInstance"))
			return consInvoke.getSubterm(5);
		return consInvoke.getSubterm(3);
	}


	public static IStrategoTerm getFieldAccessExpr(IStrategoTerm fieldAccess) {
		return fieldAccess.getSubterm(0);
	}

	public static IStrategoTerm getFieldAccessName(IStrategoTerm fieldAccess) {
		return fieldAccess.getSubterm(1);
	}


	public static IStrategoTerm getMethodInvokeName(IStrategoTerm methodInvoke) {
		return methodInvoke.getSubterm(0).getSubterm(0);
	}

	public static IStrategoTerm getMethodInvokeArgs(IStrategoTerm methodInvoke) {
		return methodInvoke.getSubterm(1).getSubterm(0);
	}


	public static int getArrayTypeDimension(IStrategoTerm arrayType) {
		int dimension = 0;
		while(arrayType != null) {
			if(isAppl(arrayType, "ArrayType", 1)) {
				++dimension;
				arrayType = getArrayTypeInnerType(arrayType);
			} else {
				break;
			}
		}
		return dimension;
	}

	public static IStrategoTerm getArrayTypeInnerType(IStrategoTerm arrayType) {
		return arrayType.getSubterm(0);
	}

	public static IStrategoTerm getArrayInitializerExprs(IStrategoTerm arrayInit) {
		return arrayInit.getSubterm(0);
	}
}