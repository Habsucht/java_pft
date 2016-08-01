package ru.stqa.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

public class GeoIpServiceTests {
    @Test
    public void testMyIp() {
        GeoIP geoIPGoogle = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("8.8.8.8");

        System.out.println(geoIPGoogle.toString());
    }
}
