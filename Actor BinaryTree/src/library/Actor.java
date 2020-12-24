package library;

import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class Actor extends Thread {
	
	private Address supervisor;
	
	public Actor() {
		this(null);
	}
	
	public Actor(Address supervisor) {
		this.supervisor = supervisor;
		this.start();
	}
	
	ConcurrentLinkedQueue<Message> mailbox = new ConcurrentLinkedQueue<>();
	
	public Address getAddress() {
		return (Message m) -> {
			mailbox.add(m);
		};
	}
	
	public void run() {
		while (true) {
			Message m = mailbox.poll();
			if (m == null) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else if (m instanceof ExceptionalMessage) {
				ExceptionalMessage m2 = (ExceptionalMessage) m;
				if (handleOrDie(m2.getException())) {
					this.getAddress().sendMessage(new SystemKillMessage());
				}
			} else {
				try {
					handleMessage(m);
				} catch (Exception e) {
					if (handleOrDie(e)) {
						this.getAddress().sendMessage(new SystemKillMessage());
					}
				}
				if (m instanceof SystemKillMessage)  {
					return;
				}
			}
		}
	}
	
	private boolean handleOrDie(Exception e) {
		boolean r = handleException(e);
		if (!r) {
			if (supervisor != null) {
				supervisor.sendMessage(new ExceptionalMessage(e));
			}
			return true;
		} else {
			return false;
		}
	}

	protected abstract void handleMessage(Message m);
	
	protected boolean handleException(Exception e) {
		return false;
	}
}
