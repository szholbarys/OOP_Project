package intranet;


import java.io.Serializable;
import java.util.Vector;

public class Organization implements Serializable{
	private static final long serialVersionUID = -4612648477718678953L;
	private String name;
	private Vector<Student> members;
	private Student leader;
	private Vector<Student> pending;

	public Organization(String name, Student leader) {
		this.name = name;
		this.leader = leader;
		this.members = new Vector<Student>();
		this.pending = new Vector<Student>();
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Student getLeader() {
		return this.leader;
	}

	public void setLeader(Student leader) {
		this.leader = leader;
		this.members.add(leader);
	}

	public Vector<Student> getMembers() {
		return this.members;
	}

	public boolean addCandidate(Student candidate) {
		if (members.contains(candidate) && pending.contains(candidate)) {
			return false;
		} else {
			this.pending.add(candidate);
			return true;
		}
	}

	public boolean leaveOrganization(Student member) {
		if (members.contains(member)) {
			members.remove(member);
			return true;
		} else
			return false;
	}

	public boolean kickMember(Student leader, Student member) {
		if (leader == this.getLeader()) {
			if (members.contains(member))
				members.remove(member);
			return true;
		} else
			return false;
	}

	public boolean addMember(Student leader, Student member) {
		if (leader == this.getLeader()) {
			if (members.contains(member)) {
				return false;
			} else {
				this.pending.remove(member);
				this.members.add(member);
				return true;
			}
		}
		return false;
	}

	public boolean rejectCandidate(Student leader, Student member) {
		if (leader == this.getLeader()) {
			if (members.contains(member)) {
				return false;
			} else if (pending.contains(member)) {
				pending.remove(member);
				return true;
			}
		}
		return false;
	}
}
