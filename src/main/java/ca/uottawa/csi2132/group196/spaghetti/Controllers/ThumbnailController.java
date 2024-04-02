package ca.uottawa.csi2132.group196.spaghetti.Controllers;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.logging.Logger;

@RestController
@RequestMapping("/thumbnail")
public class ThumbnailController {
    private static final String DEFAULT_HOTEL_THUMBNAIL_PATH = "/thumbnails/hotels/default.png";

    private final Logger logger;

    public ThumbnailController() {
        this.logger = Logger.getLogger(this.getClass().getSimpleName());
    }

    @GetMapping(value = "/hotel", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody Resource getGenericThumbnailForHotel() {
        Resource handle = new ClassPathResource(DEFAULT_HOTEL_THUMBNAIL_PATH);
        if (!handle.exists()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        try {
            return new ByteArrayResource(handle.getContentAsByteArray());
        } catch (IOException e) {
            logger.warning("Unable to read the generic hotel thumbnail.");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/hotel/{hotelId}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody Resource getThumbnailForHotel(@PathVariable String hotelId) {
        // TODO consider mapping hotelIds to thumbnails
        return getGenericThumbnailForHotel();
    }
}
