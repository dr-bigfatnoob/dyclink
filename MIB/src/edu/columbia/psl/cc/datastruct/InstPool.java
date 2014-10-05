package edu.columbia.psl.cc.datastruct;

import java.util.TreeSet;

import edu.columbia.psl.cc.pojo.InstNode;

public class InstPool extends TreeSet<InstNode> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InstPool() {
		
	}
	
	public InstPool(InstPool contents) {
		for (InstNode c: contents) {
			this.searchAndGet(c.getFromMethod(), c.getIdx(), c.getOp().getOpcode(), c.getAddInfo());
		}
	}

	public InstNode searchAndGet(String methodKey, int idx, int opcode, String addInfo) {
		for (InstNode inst: this) {
			if (inst.getFromMethod().equals(methodKey) && 
					inst.getIdx() == idx && 
					inst.getOp().getOpcode() == opcode && 
					inst.getAddInfo().equals(addInfo))
				return inst;
		}
		
		//Create new 
		InstNode probe = new InstNode();
		probe.setFromMethod(methodKey);
		probe.setIdx(idx);
		probe.setOp(BytecodeCategory.getOpcodeObj(opcode));
		probe.setAddInfo(addInfo);
		this.add(probe);
		return probe;
	}
	
	public InstNode searchAndGet(String methodKey, int idx) {
		for (InstNode inst: this) {
			if (inst.getFromMethod().equals(methodKey) && inst.getIdx() == idx)
				return inst;
		}
		
		System.err.println("Cannot find inst by idx: " + idx);
		return null;
	}

}
