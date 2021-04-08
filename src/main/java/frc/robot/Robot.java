/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.sql.Driver;
import java.sql.DriverAction;
import javax.annotation.meta.When;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Joystick.ButtonType;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.I2C;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

/*
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
//ColorTarget is all red,blue,yellow,green
private static final int ColorTarget =7;
private int redcount = 0;
private int bluecount = 0;
private int greencount = 0;
private int yellowcount = 0;

 private double forward = 0.0;
 private double turn = 0.0;
 private double backward = 0.0;

 public CameraServer server;
 public VideoSource cam0;


 //Bryce - Commented out other color sensor stuf
 /*
 private final I2C.Port ColorPort = I2C.Port.kOnboard;
 private final ColorSensorV3 m_colorSensor = new ColorSensorV3(ColorPort);
 private final ColorMatch m_colorMatcher = new ColorMatch();

 private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
 private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
 private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
 private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
 Compressor compress = new Compressor(22);//this is to start the compresser code i used 22 couse it was in the phinex tuner CW
 */

 //above code works well ndl

 WPI_TalonSRX leftController = new WPI_TalonSRX(11);
 WPI_TalonSRX rightController = new WPI_TalonSRX(12);
//  WPI_TalonSRX controlPannelMotor = new WPI_TalonSRX(13);
//  WPI_VictorSPX leftScrew = new WPI_VictorSPX(14);
//  WPI_VictorSPX rightScrew = new WPI_VictorSPX(15);
//  WPI_VictorSPX compressorSpx = new WPI_VictorSPX(16);
// Commented 
 
/*
 DoubleSolenoid exampleDouble = new DoubleSolenoid(0,1);
 boolean enabled = compress.enabled();
 boolean pressureSwitch = compress.getPressureSwitchValue();
 double current = compress.getCompressorCurrent();
 */

 Joystick Joy = new Joystick(0);
 XboxController Xbox = new XboxController(1);

 
 DigitalInput magnet = new DigitalInput(0);
 DigitalInput limit = new DigitalInput(1); 

 @Override
  public void robotInit(){
  /*
  This function is run when the robot is first started up and should be used
  for any initialization code.
  */

  //Bryce - Commented out Solonoid Stuff

  /*
  exampleDouble.set(kOff);
  exampleDouble.set(kForward);
  exampleDouble.set(kReverse);
  */

//this stuff is for silodiods
  CameraServer.getInstance().startAutomaticCapture();
  
  /*
  m_colorMatcher.addColorMatch(kBlueTarget);
  m_colorMatcher.addColorMatch(kGreenTarget);
  m_colorMatcher.addColorMatch(kRedTarget);
  m_colorMatcher.addColorMatch(kYellowTarget);  
  */
  
 }
 
  DifferentialDrive drive = new DifferentialDrive(leftController, rightController);

 @Override
  public void autonomousInit() {
 }

 @Override
  public void autonomousPeriodic() {   
 }

 @Override
  public void teleopInit() {

    leftController.configFactoryDefault();
    rightController.configFactoryDefault();  

    leftController.setInverted(false);
    rightController.setInverted(true);
    
    //drive.setRightSideInverted(false); - This shouldn't be neccesary BE
  
  }

 @Override
  public void teleopPeriodic() {

  //Bryce - Commentted out the color detection

  /*
    Color detectedColor = m_colorSensor.getColor();
    String colorString;
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    if (match.color == kBlueTarget) {
      colorString = "Blue";
    } else if (match.color == kRedTarget) {
      colorString = "Red";
    } else if (match.color == kGreenTarget) {
      colorString = "Green";
    } else if (match.color == kYellowTarget) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }
               
    checkcolor(colorString); //---> diff from colorStrin at bottom
      if (redcount >= ColorTarget || bluecount >= ColorTarget 
            || yellowcount >= ColorTarget || greencount >= ColorTarget){
              controlPannelMotor.set(0);
    }
    */

     magnet.get();
     limit.get();
     //double forward = +.8 * joy_blac.getY();
     //double turn = +.8 * joy_blac.getZ();
     //double backward = .8* joy_blac.getX();
     forward = +.8* Xbox.getY();  
     turn = +.8 * Xbox.getX();
     backward = +.8* Xbox.getY();

     if (Math.abs(forward) < 0.4) {
			forward = 0;
    }

    if (Math.abs(turn) < 0.4) {
			turn = 0;
    }

    if (Joy.getRawButton(1)==true) {
    controlPannelMotor.set(.30);
    }
    else if (Joy.getRawButton(1)==false){
     controlPannelMotor.set(0);
    }
    if(Joy.getRawButton(2)==true){

      exampleDouble.set(kOff);
    }

      
      }
    //these if statemeants are for siolidois CW 2/29
       if(Joy.getRawButton(4)==true) {
        exampleDouble.set(kReverse);
        System.out.println("nfdfbgbvmbxmcnmnfmsdnvmxncncnnnnnnnnxxssssadfasfknladJFHLD:HmznC");
        
      }
      else if (Joy.getRawButton(3)==true){
        exampleDouble.set(kForward);
        System.out.println("ghjkkkkkkkooooooooooooooooooooooooooooooooooooooooooooooooo");
      }
    System.out.println("JoyY:" + forward + "  turn:" + turn + " joyX " + backward);

    drive.arcadeDrive(forward, turn);
  
    //Bryce - Commented out other color things

    /*
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);  
    SmartDashboard.putBoolean("pressureSwitch:", enabled);
    */

  }
   @Override
  public void testInit() {
  }

  @Override
   public void testPeriodic() {
  }

  /*
  public void checkcolor (String fncolor){
   boolean read = false;
   if (read == false){
   if (fncolor.equals("Red")){
    redcount++;
    read = true;
  }
    else if (fncolor.equals("Blue")){
     bluecount++;
     read = true;
  }
    else if (fncolor.equals("Yellow")){
     yellowcount++;
     read = true;
  }
    else if (fncolor.equals("Green")){
     greencount++;
     read = true;
  }
  */
 } 
}




}
