package boa.compiler.ast.statements;

import boa.compiler.ast.Identifier;
import boa.compiler.ast.types.AbstractType;
import boa.compiler.visitors.AbstractVisitor;
import boa.compiler.visitors.AbstractVisitorNoArg;
import boa.compiler.visitors.AbstractVisitorNoReturn;
import boa.parser.Token;

/**
 * 
 * @author rdyer
 * @author hridesh
 */
public class TypeDecl extends Statement {
	protected Identifier identifier;
	protected AbstractType t;

	public Identifier getId() {
		return identifier;
	}

	public boolean hasType() {
		return t != null;
	}

	public AbstractType getType() {
		return t;
	}

	public TypeDecl(final Identifier identifier, final AbstractType t) {
		if (identifier != null)
			identifier.setParent(this);
		if (t != null)
			t.setParent(this);
		this.identifier = identifier;
		this.t = t;
	}

	/** {@inheritDoc} */
	@Override
	public <T,A> T accept(final AbstractVisitor<T,A> v, A arg) {
		return v.visit(this, arg);
	}

	/** {@inheritDoc} */
	@Override
	public <A> void accept(final AbstractVisitorNoReturn<A> v, A arg) {
		v.visit(this, arg);
	}

	/** {@inheritDoc} */
	@Override
	public void accept(final AbstractVisitorNoArg v) {
		v.visit(this);
	}

	public TypeDecl clone() {
		final TypeDecl d = new TypeDecl(identifier.clone(), t.clone());
		copyFieldsTo(d);
		return d;
	}

	public TypeDecl setPositions(final Token first, final Token last) {
		return (TypeDecl)setPositions(first.beginLine, first.beginColumn, last.endLine, last.endColumn);
	}
}
