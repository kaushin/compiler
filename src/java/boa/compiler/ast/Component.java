package boa.compiler.ast;

import boa.compiler.ast.Node;
import boa.compiler.ast.types.AbstractType;
import boa.compiler.visitors.AbstractVisitor;
import boa.compiler.visitors.AbstractVisitorNoArg;
import boa.compiler.visitors.AbstractVisitorNoReturn;

/**
 * 
 * @author rdyer
 * @author hridesh
 */
public class Component extends AbstractType {
	protected Identifier id;
	protected AbstractType t;

	public boolean hasIdentifier() {
		return id != null;
	}

	public Identifier getIdentifier() {
		return id;
	}

	public void setIdentifier(final Identifier id) {
		id.setParent(this);
		this.id = id;
	}

	public AbstractType getType() {
		return t;
	}

	public void setType(final AbstractType t) {
		t.setParent(this);
		this.t = t;
	}

	public Component () {
	}

	public Component (final AbstractType t) {
		this(null, t);
	}

	public Component (final Identifier id, final AbstractType t) {
		if (id != null)
			id.setParent(this);
		if (t != null)
			t.setParent(this);
		this.id = id;
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

	public Component clone() {
		final Component c = new Component(id.clone(), t.clone());
		copyFieldsTo(c);
		return c;
	}

	public Component setPositions(final Node first, final Node last) {
		if (first == null)
			return (Component)setPositions(last.beginLine, last.beginColumn, last.endLine, last.endColumn);
		return (Component)setPositions(first.beginLine, first.beginColumn, last.endLine, last.endColumn);
	}
}
