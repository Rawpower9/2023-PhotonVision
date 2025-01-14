package frc.robot.commands;

import java.util.Set;

import org.photonvision.PhotonUtils;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.PhotonVision;

public class PhotonInfo extends CommandBase{
    PhotonVision pv;
    static double CAMERA_HEIGHT_METERS = 0.6477;
    static double TARGET_HEIGHT_METERS = 1.17602;
    static double CAMERA_YAW = 24.5;


    public PhotonInfo(){
        pv = new PhotonVision();
    }
    

    public double getDistance(){
        PhotonPipelineResult result = pv.getLatestPipeline();
        PhotonTrackedTarget target = result.getBestTarget();
        double dist = 0.0;
       
        if(result.hasTargets()){
            dist = PhotonUtils.calculateDistanceToTargetMeters(CAMERA_HEIGHT_METERS, 
            TARGET_HEIGHT_METERS, Units.degreesToRadians(CAMERA_YAW), Units.degreesToRadians(target.getPitch()));   
        }
        else{
            dist = 0.69;
        }
        return dist;

    }


    public void logInfo(){
        PhotonPipelineResult result = pv.getLatestPipeline();
        PhotonTrackedTarget target = result.getBestTarget();
        System.out.print("String: ");
        System.out.println(result.toString());
        System.out.print("Has Targets: ");
        System.out.println(result.hasTargets());
        System.out.print("Number Targets: ");
        System.out.println(result.getTargets().size());
        System.out.print("Area: ");
        System.out.println(pv.getArea(target));
        System.out.print("April Tag ID: ");
        System.out.println(target.getFiducialId());
        System.out.print("Pitch: ");
        System.out.println(target.getPitch());
        System.out.print("Yaw: ");
        System.out.println(target.getYaw());
        System.out.print("Skew: ");
        System.out.println(target.getSkew());
    }
}
