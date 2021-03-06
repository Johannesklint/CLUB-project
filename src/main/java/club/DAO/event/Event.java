package club.DAO.event;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalAmount;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import club.DAO.post.Post;
import club.DAO.user.User;

@Entity
@DiscriminatorValue(value="EVENT")
@NamedQueries({
    @NamedQuery(name="Event.findAll", query="SELECT e FROM Event e")
}) 
public class Event extends Post {
	
	private Timestamp startTime;
	private int durationInMinutes;
	@ManyToMany
	private List<User> attendees;
	
	public Event() {}

	public Timestamp getStartTime() {
		return startTime;
	}


	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public int getDurationInMinutes() {
		return durationInMinutes;
	}

	public void setDurationInMinutes(int durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
	}

	public List<User> getAttendees() {
		return attendees;
	}

	public void setAttendees(List<User> attendees) {
		this.attendees = attendees;
	}
	
	public Timestamp getEndTime() {
		TemporalAmount minutes = Duration.ofMinutes(this.durationInMinutes);
		Instant startTimeAsInstant = startTime.toInstant();
		Instant endTime = startTimeAsInstant.plus(minutes); 
		return Timestamp.from(endTime);
	}


}
