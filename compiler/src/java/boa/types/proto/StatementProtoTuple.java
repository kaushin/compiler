package boa.types.proto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import boa.types.BoaInt;
import boa.types.BoaProtoList;
import boa.types.BoaProtoTuple;
import boa.types.BoaType;

/**
 * A {@link StatementProtoTuple}.
 * 
 * @author rdyer
 */
public class StatementProtoTuple extends BoaProtoTuple {
	private final static List<BoaType> members = new ArrayList<BoaType>();
	private final static Map<String, Integer> names = new HashMap<String, Integer>();

	static {
		names.put("kind", 0);
		members.add(new BoaInt());

		names.put("comments", 1);
		members.add(new BoaProtoList(new CommentProtoTuple()));

		names.put("statements", 2);
		members.add(new BoaProtoList(new StatementProtoTuple()));

		names.put("initializations", 3);
		members.add(new BoaProtoList(new ExpressionProtoTuple()));

		names.put("condition", 4);
		members.add(new ExpressionProtoTuple());

		names.put("updates", 5);
		members.add(new BoaProtoList(new ExpressionProtoTuple()));

		names.put("variable_declaration", 6);
		members.add(new VariableProtoTuple());

		names.put("type_declaration", 7);
		members.add(new DeclarationProtoTuple());

		names.put("expression", 8);
		members.add(new ExpressionProtoTuple());
	}

	/**
	 * Construct a {@link StatementProtoTuple}.
	 */
	public StatementProtoTuple() {
		super(members, names);
	}

	@Override
	public String toJavaType() {
		return "boa.types.Ast.Statement";
	}
}