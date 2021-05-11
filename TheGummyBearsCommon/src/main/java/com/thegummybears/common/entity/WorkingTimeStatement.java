package com.thegummybears.common.entity;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "workingtime_statement")
public class WorkingTimeStatement extends Base{
	@Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "start_work")
    private LocalTime startWork;

    @Column(name = "finish_work")
    private LocalTime finishWork;

    
    
    
	public WorkingTimeStatement() {
		super();
	}


	public WorkingTimeStatement(Long id, User user, LocalDate date, LocalTime startWork, LocalTime finishWork) {
		this.id = id;
		this.user = user;
		this.date = date;
		this.startWork = startWork;
		this.finishWork = finishWork;
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public User getUser() {
		return user;
	}




	public void setUser(User user) {
		this.user = user;
	}




	public LocalDate getDate() {
		return date;
	}




	public void setDate(LocalDate date) {
		this.date = date;
	}




	public LocalTime getStartWork() {
		return startWork;
	}




	public void setStartWork(LocalTime startWork) {
		this.startWork = startWork;
	}




	public LocalTime getFinishWork() {
		return finishWork;
	}




	public void setFinishWork(LocalTime finishWork) {
		this.finishWork = finishWork;
	}
	
	
    
    
}
