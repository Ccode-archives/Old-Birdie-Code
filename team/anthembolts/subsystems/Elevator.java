/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package team.anthembolts.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team.anthembolts.Robot;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
    public int target = 0;
    double Position;
    final int lev1 = 1200;
    final int lev2=18200;
    final int lev3=36000;
    final int del=700;

  @Override
  public void initDefaultCommand() {
    Position=-1;
    target=0;
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  

  @Override
  public void periodic() {

      if (Position == -1)
      {
          Position = lev3;
          target =0;
      }
      else if (target!=0){
          Position = Math.abs(Robot.sensors.encLift.getDistance());
      }

    // super.periodic();
     // if(Robot.sensors.encLift.getDistance()<10000-300){
    if (Math.abs(Robot.oi.myXBox.getRawAxis(1))>.15){
      Robot.actuators.liftMotor.set(Robot.oi.myXBox.getRawAxis(1)*.3);
    }
    else{
    if(Robot.oi.myXBox.getRawButton(4)){
        target = lev3;
      }
    if(Robot.oi.myXBox.getRawButton(2)||Robot.oi.myXBox.getRawButton(3)){
        target = lev2;
      }
    if(Robot.oi.myXBox.getRawButton(1)){
        target = lev1;
      }
      // && level2.get() == true && level3.get() == true
      if (Math.abs(target-Position)>del) {
        Robot.actuators.liftMotor.set(0.65*Math.copySign(1, -(target-Position)));
        // System.out.println("Lift engaged");
      }
      else {
        Robot.actuators.liftMotor.stopMotor();
        // System.out.println("Lift stopped");
        SmartDashboard.putData("Hall", Robot.sensors.level1);
        //seems legit
      }
      if (Robot.sensors.level1.get() == false){
        Position=0;
        target=lev1;
        Robot.sensors.encLift.reset();
        Robot.actuators.liftMotor.stopMotor();
      }

     
    }
 }
}
