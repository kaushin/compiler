package boa.compiler.ast.types;

import boa.compiler.visitors.AbstractVisitor;
import boa.compiler.visitors.AbstractVisitorNoArg;
import boa.compiler.visitors.AbstractVisitorNoReturn;
import boa.parser.Token;

/**
 * 
 * @author rdyer
 * @author hridesh
 */
public class VisitorType extends AbstractType {
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

	public VisitorType clone() {
		final VisitorType t = new VisitorType();
		copyFieldsTo(t);
		return t;
	}

	public VisitorType setPositions(final Token first) {
		return (VisitorType)setPositions(first.beginLine, first.beginColumn, first.endLine, first.endColumn);
	}
}
