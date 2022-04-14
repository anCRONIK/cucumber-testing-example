import { Image } from 'semantic-ui-react';
import { useNavigate } from 'react-router-dom';

export const NavbarLogo = () => {
  const navigate = useNavigate();
  const imageClick = () => {
    navigate('/');
  };

  return (
    <Image
      size="mini"
      src="https://react.semantic-ui.com/logo.png"
      onClick={() => imageClick()}
    />
  );
};
