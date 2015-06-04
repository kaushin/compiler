package boa.compiler.ast;

import boa.compiler.ast.Node;
import boa.compiler.visitors.AbstractVisitor;
import boa.compiler.visitors.AbstractVisitorNoArg;
import boa.compiler.visitors.AbstractVisitorNoReturn;
import boa.parser.Token;

/**
 * 
 * @author rdyer
 * @author hridesh
 */
public class Selector extends Node {
	protected Identifier id;

	public Identifier getId() {
		return id;
	}

	public Selector (final Identifier id) {
		if (id != null)
			id.setParent(this);
		this.id = id;
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

	public Selector clone() {
		final Selector s = new Selector(id.clone());
		copyFieldsTo(s);
		return s;
	}

	public Selector setPositions(final Token first, final Node last) {
		return (Selector)setPositions(first.beginLine, first.beginColumn, last.endLine, last.endColumn);
	}
}
