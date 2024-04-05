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
    private static final String HOTEL_THUMBNAIL_PATH_PREFIX = "/thumbnails/hotels";
    private static final String ROOM_THUMBNAIL_PATH_PREFIX = "/thumbnails/rooms";
    private static final String DEFAULT_HOTEL_THUMBNAIL_PATH = HOTEL_THUMBNAIL_PATH_PREFIX + "/default.png";
    private static final String DEFAULT_ROOM_THUMBNAIL_PATH = ROOM_THUMBNAIL_PATH_PREFIX + "/default.png";

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
        if (hotelId == null) return getGenericThumbnailForRoom();

        Resource handle = new ClassPathResource(String.format("%s/%s.png", HOTEL_THUMBNAIL_PATH_PREFIX, hotelId));
        if (!handle.exists()) {
            logger.warning(String.format("Couldn't get hotel thumbnail for hotel %s. Defaulting to generic thumbnail...", hotelId));
            return getGenericThumbnailForRoom();
        }

        try {
            return new ByteArrayResource(handle.getContentAsByteArray());
        } catch (IOException ex) {
            logger.warning(String.format("Unable to read the hotel thumbnail for hotel %s (%s). Defaulting to generic thumbnail...", hotelId, ex));
            return getGenericThumbnailForRoom();
        }
    }

    @GetMapping(value = "/room", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody Resource getGenericThumbnailForRoom() {
        Resource handle = new ClassPathResource(DEFAULT_ROOM_THUMBNAIL_PATH);
        if (!handle.exists()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        try {
            return new ByteArrayResource(handle.getContentAsByteArray());
        } catch (IOException e) {
            logger.warning("Unable to read the generic hotel thumbnail.");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/room/{hotelId}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody Resource getThumbnailForRoom(@PathVariable String hotelId) {
        if (hotelId == null) return getGenericThumbnailForRoom();

        Resource handle = new ClassPathResource(String.format("%s/%s.png", ROOM_THUMBNAIL_PATH_PREFIX, hotelId));
        if (!handle.exists()) {
            logger.warning(String.format("Couldn't get room thumbnail for hotel %s. Defaulting to generic thumbnail...", hotelId));
            return getGenericThumbnailForRoom();
        }

        try {
            return new ByteArrayResource(handle.getContentAsByteArray());
        } catch (IOException ex) {
            logger.warning(String.format("Unable to read the room thumbnail for hotel %s (%s). Defaulting to generic thumbnail...", hotelId, ex));
            return getGenericThumbnailForRoom();
        }
    }
}
