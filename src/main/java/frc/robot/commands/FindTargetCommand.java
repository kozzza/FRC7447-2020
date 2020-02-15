  
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Limelight;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID;

public class FindTargetCommand extends Command {
  
  double mmspeed = 0.55; //micromove speed
  double mtspeed = 0.43; //microturn speed
  double totalError = 0;
  double lastError = 0;


  public FindTargetCommand() {
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("Initializing");
    totalError = 0;
  } 

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double move = Robot.oi.stick.getTriggerAxis(GenericHID.Hand.kRight) - Robot.oi.stick.getTriggerAxis(GenericHID.Hand.kLeft);
    double boost = -Robot.oi.stick.getY(Hand.kRight)/2;
    float kP = 0.05f;  // Proportional control constant
    float kI = 0.005f;
    float kD = 0.05f;

    //Limelight limelight = new Limelight();
    
    double tx = Limelight.getTx();
    System.out.println(tx);

    if (tx < 10 && tx > -10) {
      totalError = totalError + tx;
    }
    System.out.println("totalE2: " + totalError);

    double dError = (tx - lastError);

    double turnVoltage = kP*tx + kD * dError + kI * totalError;

    //Robot.driveTrain.manualDrive(move * (mmspeed + boost), turnVoltage);
    lastError = tx;
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
    end();
  }
}