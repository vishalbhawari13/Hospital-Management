/*
package Hospital.H1;

import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.OSMTileFactoryInfo;
import org.jdesktop.swingx.mapviewer.DefaultTileFactory;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.Waypoint;
import org.jdesktop.swingx.mapviewer.WaypointPainter;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class HospitalLocatorApp {

    private static final String API_KEY = "YOUR_GOOGLE_API_KEY";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Hospital Locator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create a map viewer
        JXMapKit mapKit = new JXMapKit();
        mapKit.setDefaultProvider(DefaultTileFactory.createTileFactory(
                new OSMTileFactoryInfo()));

        panel.add(mapKit, BorderLayout.CENTER);

        // Create a button to fetch nearby hospitals
        JButton locationButton = new JButton("Fetch Nearby Hospitals");
        locationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchNearbyHospitals(mapKit.getCenterPosition());
            }
        });

        panel.add(locationButton, BorderLayout.SOUTH);

        frame.setContentPane(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void fetchNearbyHospitals(GeoPosition centerPosition) {
        // You can use the center position to get the user's location
        double latitude = centerPosition.getLatitude();
        double longitude = centerPosition.getLongitude();

        // Implement the logic to fetch nearby hospitals using the Google Places API or any other service
        // ...

        // Display the nearby hospitals on the map (replace this with your logic)
        List<Waypoint> waypoints = new ArrayList<>();
        waypoints.add(new Waypoint(latitude + 0.01, longitude + 0.01));
        waypoints.add(new Waypoint(latitude - 0.01, longitude - 0.01));

        WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();
        waypointPainter.setWaypoints(waypoints);

        JXMapViewer mapViewer = new JXMapViewer();
        mapViewer.setOverlayPainter(waypointPainter);

        // Update the existing mapKit with the new mapViewer
        JFrame mapFrame = new JFrame("Nearby Hospitals");
        mapFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mapFrame.setSize(600, 400);
        mapFrame.setLayout(new BorderLayout());
        mapFrame.add(mapViewer, BorderLayout.CENTER);
        mapFrame.setLocationRelativeTo(null);
        mapFrame.setVisible(true);
    }
}
*/
