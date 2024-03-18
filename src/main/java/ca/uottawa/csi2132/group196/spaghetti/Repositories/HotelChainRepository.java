package ca.uottawa.csi2132.group196.spaghetti.Repositories;

import ca.uottawa.csi2132.group196.spaghetti.Embeddables.Address;
import ca.uottawa.csi2132.group196.spaghetti.Entities.HotelChain.HotelChain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HotelChainRepository extends CrudRepository<HotelChain, String> {
    @Query("SELECT COUNT(*) FROM Hotel hotel WHERE hotel.owner.chainName = :chainName")
    int getHotelCountByChainName(@Param("chainName") String chainName);

    @Query("SELECT chain FROM HotelChain chain JOIN FETCH chain.addresses WHERE chain.chainName = :chainName")
    List<Address> findAddressesByChainName(@Param("chainName") String chainName);

    HotelChain getHotelChainByChainName(String chainName);
}
