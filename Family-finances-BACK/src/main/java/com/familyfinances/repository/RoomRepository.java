package com.familyfinances.repository;

import com.familyfinances.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("SELECT r FROM Room r WHERE r.name = ?1")
    Room findByRoomName(String name);

    @Query("DELETE FROM Room r WHERE r.name = ?1")
    boolean deleteRoomByName(String name);
}
