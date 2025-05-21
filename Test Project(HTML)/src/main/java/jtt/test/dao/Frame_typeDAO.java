package jtt.test.dao;

import java.sql.SQLException;

import jtt.test.dto.Archetype;
import jtt.test.dto.FrameType;

public interface Frame_typeDAO extends GenericDAO<FrameType> {
	FrameType getByName(String name) throws SQLException;
}
