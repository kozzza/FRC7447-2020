/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;


public class ArmStallCommand extends Command {
  public int armdirect;
  public boolean end;
  public boolean finish;
  

  public ArmStallCommand() {
    finish = false;
    end = true;
    armdirect = 1;

    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    end = true;
    setTimeout(3);
    while(end == true) {
      armdirect = armdirect * -1;
      System.out.println("1: alternate");
    }
    
  }
  

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (armdirect== -1 && isTimedOut()) {
    
      Robot.armRotation.turn(0.1);

      }
    
    else if (armdirect == -1 && ! isTimedOut()){
      Robot.armRotation.turn(0.35 * armdirect);

    }

    else if(armdirect == 1 && isTimedOut()){
      finish = true;
    }

    else {
      Robot.armRotation.turn(.35 * armdirect);
    }

      
  }
  

    
    
  

  

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return finish;
    
    
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.println("2: End");
    
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
