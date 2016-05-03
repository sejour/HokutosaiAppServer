package hokutosai.server.data.entity.events;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "schedules")
@Data
public class Schedule {

	@Id
	@Column(name = "id")
	@JsonProperty("schedule_id")
	private Integer scheduleId;

	@Column(name = "date", nullable = false)
	@JsonProperty("date")
	private Date date;

	@Column(name = "day", nullable = false)
	@JsonProperty("day")
	private String day;

	@Transient
	@JsonProperty("timetable")
	private List<SimpleEvent> timetable;

}
