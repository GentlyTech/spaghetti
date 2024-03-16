package ca.uottawa.csi2132.group196.spaghetti.Repositories;

import ca.uottawa.csi2132.group196.spaghetti.Entities.IdClasses.AddressId;
import ca.uottawa.csi2132.group196.spaghetti.Entities.HotelChain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HotelChainRepository extends CrudRepository<HotelChain, String> {
    //@Query("") // TODO implement getHotelCount() query annotation
    //int getHotelCount();

    @Query("SELECT chain FROM HotelChain chain JOIN FETCH chain.addresses WHERE chain.chainName = :chainName")
    List<AddressId> findAddressesByChainName(@Param("chainName") String chainName);
    
    HotelChain findHotelChainByChainName(String chainName);
}
