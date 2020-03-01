/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;


public class ToggleAutonColorSpinner extends Command {
  private int cycle;
  public ToggleAutonColorSpinner() {
    // Use requires() here to declare subsystem dependencies
    cycle = 0;
   requires(Robot.wheelSpinner);
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if (cycle > 5) {
      cycle = 1;
    }
   
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    Robot.wheelSpinner.colorChange(cycle);
    

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    cycle += 1;
    
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    
  }
}
