import { Menu } from 'semantic-ui-react';
import { NavbarLogo } from './NavbarLogo';

export const NavBarDesktop = (props) => {
  const { leftItems, rightItems } = props;

  return (
    <Menu fixed="top" inverted>
      <Menu.Item>
        <NavbarLogo />
      </Menu.Item>

      {leftItems.map((item) => (
        <Menu.Item {...item} />
      ))}

      <Menu.Menu position="right">
        {rightItems != null &&
          rightItems.map((item) => <Menu.Item {...item} />)}
      </Menu.Menu>
    </Menu>
  );
};
