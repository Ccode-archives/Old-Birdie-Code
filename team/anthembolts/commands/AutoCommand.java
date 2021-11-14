/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package team.anthembolts.commands;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team.anthembolts.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;

public class AutoCommand extends Command {

  private final Timer m_timer = new Timer();
  private static double checker = 0;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  
  private final int AUTO_LEFT = 1;
  private final int AUTO_RIGHT = 3;
  private final int AUTO_MIDDLE = 2;


  public AutoCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    m_timer.reset();
    m_timer.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // Drive for 3 seconds
    /*if (m_timer.get() < 2.0) 
      Robot.drive.Drive(-0.6, 0);  
    else
      Robot.drive.stopMotor();*/
      // middlePath();

      switch ((int) SmartDashboard.getNumber("Robot Position", 1.0) ) {
        case AUTO_LEFT:
                left();
                break;
        case AUTO_MIDDLE:
                middle();
                break;
        case AUTO_RIGHT:
                right();
                break;
      }


  }

  protected void middle()
  {
    double t = m_timer.get();

    if (t < 3)
      driveStraight(0,-0.65, 2);

    if (t > 2 && t < 4)
      turn(-30, -0.6);

    if (t > 4 && t < 6)
      driveStraight(-30,-0.65, t + 2);  

    if (t > 6 && t < 8)
      turn(0, 0.6);
      
    if (t > 8 && t < 9.5)
      driveStraight(0,-0.65, t + 2);
  }

  protected void left()
  {
    double t = m_timer.get();

    if (t < 7)
      driveStraight(0,-0.65, 5);

    if (t > 7 && t < 9)
      turn(90, 0.6);

    if (t > 9 && t < 11)
      driveStraight(90,-0.65, t + 2);  
  }


  protected void right()
  {
    double t = m_timer.get();

    if (t < 7)
      driveStraight(0,-0.65, 5);

    if (t > 7 && t < 9)
      turn(-90, -0.6);

    if (t > 9 && t < 11)
      driveStraight(-90,-0.65, t + 2);  
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }

  private boolean driveStraight(double angle, double speed, double time){
    //double start = m_timer.get();
      if (m_timer.get() <time){
      Robot.drive.driveStraight(speed, angle); 
      return false; 
    }
    else{return true;}
    //Robot.drive.stopMotor();
  }

  private void turn(double target,double speed)
  {
    if (Math.abs(Robot.sensors.myGyro.getAngle()-target)>.1)
      Robot.drive.turn(Math.copySign(speed, target-Robot.sensors.myGyro.getAngle()));
    else 
      Robot.drive.stopMotor();
  }

  // protected void middlePath()
  // {
  //   double t = m_timer.get();

  //   if (t < 5)
  //     driveStraight(-0.6, 5);

  //   if (t >= 5 && t < 8)
  //     turn(-90, -0.6);

  //   if (t > 8 && t < 10)
  //     driveStraight(-0.6, t + 2);  

  //   if (t >= 10 && t < 13)
  //     turn(0, 0.6);
      
  //   if (t > 13 && t <= 15)
  //     driveStraight(-0.6, t + 2);
  // }
 
  
}
